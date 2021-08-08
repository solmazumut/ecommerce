package models

type UserMadeAddBasketOperation struct {
	ProductId            int64   `json:"productId"`
	ProductTitle         string  `json:"productTitle"`
	ProductImageUrl      string  `json:"productImageUrl"`
	SellerId             int64   `json:"sellerId"`
	Price                float32 `json:"price"`
	Quantity                int     `json:"quantity"`
	UserId               int64   `json:"userId"`
	TopicName            string  `json:"topicName"`
}

func NewUserMadeAddBasketOperation (productId int64, productTitle string,
	productImageUrl string, sellerId int64, price float32, topicName string,
	quantity int, userId int64) UserMadeAddBasketOperation {
	return UserMadeAddBasketOperation{
		ProductId: productId,
		ProductTitle: productTitle,
		ProductImageUrl: productImageUrl,
		SellerId: sellerId,
		Price: price,
		Quantity: quantity,
		UserId: userId,
		TopicName: topicName,
	}
}
