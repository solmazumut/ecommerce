package elasticSearch

import (
	"CouchbaseElasticSearchConnectorService/config"
	"fmt"
	elastic "gopkg.in/olivere/elastic.v7"
)

func GetESClient() (*elastic.Client, error) {

	client, err :=  elastic.NewClient(elastic.SetURL(config.ReadConfigVariable().ElasticsearchConfig.Url),
		elastic.SetSniff(false),
		elastic.SetHealthcheck(false))

	fmt.Println("ES initialized...")

	return client, err

}