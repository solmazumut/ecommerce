package services

import (
	"CouchbaseElasticSearchConnectorService/models"
	"context"
	"encoding/json"
	"fmt"
	"gopkg.in/olivere/elastic.v7"
)

type elasticSearchConnection struct {
	BackgroundContext   context.Context
	ElasticSearchClient *elastic.Client
}



func NewElasticSearchConnection(ctx context.Context, esClient *elastic.Client) *elasticSearchConnection {
	return &elasticSearchConnection{
		BackgroundContext:   ctx,
		ElasticSearchClient: esClient,
	}
}


func sendQueryAndReturnRespond(query string, id int64,
		connection *elasticSearchConnection)[]*elastic.SearchHit {
	searchSource := elastic.NewSearchSource()
	searchSource.Query(elastic.NewMatchQuery(query, id))
	queryStr, err1 := searchSource.Source()
	queryJs, err2 := json.Marshal(queryStr)
	if err1 != nil || err2 != nil {
		fmt.Println("[esClient][GetResponse]err during query marshal=", err1, err2)
	}
	fmt.Println("[esClient]Final ESQuery=\n", string(queryJs))
	searchService := connection.
		ElasticSearchClient.Search().Index("baskets").SearchSource(searchSource)

	searchResult, err3 := searchService.Do(connection.BackgroundContext)
	if err3 != nil {
		fmt.Println("[BasketsES][GetPIds]Error=", err3)
	}
	var hit = searchResult.Hits.Hits
	return hit
}

func sendStringQueryAndReturnRespond(query string, queryData string,
	connection *elasticSearchConnection)[]*elastic.SearchHit {
	searchSource := elastic.NewSearchSource()
	searchSource.Query(elastic.NewMatchQuery(query, queryData))
	queryStr, err1 := searchSource.Source()
	queryJs, err2 := json.Marshal(queryStr)
	if err1 != nil || err2 != nil {
		fmt.Println("[esClient][GetResponse]err during query marshal=", err1, err2)
	}
	fmt.Println("[esClient]Final ESQuery=\n", string(queryJs))
	searchService := connection.
		ElasticSearchClient.Search().Index("baskets").SearchSource(searchSource)

	searchResult, err3 := searchService.Do(connection.BackgroundContext)
	if err3 != nil {
		fmt.Println("[BasketsES][GetPIds]Error=", err3)
	}
	var hit = searchResult.Hits.Hits
	return hit
}


func sendQueryAndReturnBaskets(query string, queryData string,
	connection *elasticSearchConnection) []models.Basket {

	var baskets []models.Basket
	hit := sendStringQueryAndReturnRespond(query, queryData, connection)
	foundSize := len(hit)
	notFound := 0
	if(foundSize > notFound) {
		for _, basketInRespond := range hit{
			var basket models.Basket
			err := json.Unmarshal(basketInRespond.Source, &basket)
			if err != nil {
				fmt.Println("[Getting Baskets][Unmarshal] Err=", err)
			}
			baskets = append(baskets, basket)
		}
	}
	return baskets
}

func isBasketExist(userId int64, connection *elasticSearchConnection) bool {
	foundBasket := getBasketByUserId(userId, connection)
	isExist := true
	if foundBasket.UserId == 0 &&
		len(foundBasket.Sellers) == 0 &&
		len(foundBasket.Promotions) == 0 {
		isExist = false
	}
	return isExist
}

func addBasketToElasticSearch(basket models.Basket, connection *elasticSearchConnection)  {
	isExist := isBasketExist(basket.UserId, connection)
	if !isExist {
		dataJSON, err := json.Marshal(basket)
		js := string(dataJSON)
		ind, err := connection.ElasticSearchClient.Index().
			Index("baskets").
			BodyJson(js).
			Do(connection.BackgroundContext)

		if err != nil {
			panic(err)
		}
		fmt.Println(ind)
		fmt.Println("[Elastic][InsertBasket]Insertion Successful")
	} else {
		fmt.Println("Error! There is a basket with the same ID")
	}

}

func getBasketByUserId(basketId int64, connection *elasticSearchConnection) models.Basket {
	var basket models.Basket
	searchSource := elastic.NewSearchSource()
	searchSource.Query(elastic.NewMatchQuery("userId", basketId))
	queryStr, err1 := searchSource.Source()
	queryJs, err2 := json.Marshal(queryStr)
	if err1 != nil || err2 != nil {
		fmt.Println("[esClient][GetResponse]err during query marshal=", err1, err2)
	}
	fmt.Println("[esClient]Final ESQuery=\n", string(queryJs))
	searchService := connection.
		ElasticSearchClient.Search().Index("baskets").SearchSource(searchSource)

	searchResult, err3 := searchService.Do(connection.BackgroundContext)
	if err3 != nil {
		fmt.Println("[BasketsES][GetPIds]Error=", err3)
	}
	var hit = searchResult.Hits.Hits
	foundSize := len(hit)
	notFound := 0
	if(foundSize > notFound) {
		basketJson := hit[0].Source
		err4 := json.Unmarshal(basketJson, &basket)
		if err4 != nil {
			fmt.Println("[Getting Baskets][Unmarshal] Err=", err4)
		}
	}

	return basket
}

func updateBasket(basket models.Basket, connection *elasticSearchConnection)  {
	arr := sendQueryAndReturnRespond("userId", basket.UserId, connection)
	if(len(arr) > 0) {
		id := arr[0].Id
		update, err := connection.ElasticSearchClient.Update().Index("baskets").Id(id).
			Doc(map[string]interface{}{"userId": basket.UserId, "sellers": basket.Sellers, "promotions": basket.Promotions}).
			Do(connection.BackgroundContext)
		if(err != nil) {
			fmt.Println(err)
		} else {
			fmt.Println(update)
		}
	}
}

func deleteBasketWithId(userId int64, connection *elasticSearchConnection)  {
	arr := sendQueryAndReturnRespond("userId", userId, connection)
	if(len(arr) > 0) {
		id := arr[0].Id
		res, err := connection.ElasticSearchClient.Delete().Index("baskets").
			Id(id).
			Do(context.Background())

		if(err != nil) {
			fmt.Println(err)
		} else {
			fmt.Println(res)
		}
	}
}

func createOrUpdateBasket(basket models.Basket, connection *elasticSearchConnection) {
	arr := sendQueryAndReturnRespond("userId", basket.UserId, connection)
	if len(arr) < 1 {
		dataJSON, err := json.Marshal(basket)
		js := string(dataJSON)
		ind, err := connection.ElasticSearchClient.Index().
			Index("baskets").
			BodyJson(js).
			Do(connection.BackgroundContext)

		if err != nil {
			panic(err)
		}
		fmt.Println(ind)
		fmt.Println("[Elastic][InsertBasket]Insertion Successful")
	} else {
		id := arr[0].Id
		update, err := connection.ElasticSearchClient.Update().Index("baskets").Id(id).
			Doc(map[string]interface{}{"userId": basket.UserId, "sellers": basket.Sellers, "promotions": basket.Promotions}).
			Do(connection.BackgroundContext)
		if(err != nil) {
			fmt.Println(err)
		} else {
			fmt.Println(update)
		}
	}
}