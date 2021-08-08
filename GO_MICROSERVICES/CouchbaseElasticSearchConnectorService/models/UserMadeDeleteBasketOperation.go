package models

type UserMadeDeleteBasketOperation struct {
	ProductId            int64   `json:"productId"`
	SellerId             int64   `json:"sellerId"`
	UserId               int64   `json:"userId"`
	TopicName            string  `json:"topicName"`
}

func NewUserMadeDeleteBasketOperation (productId int64, topicName string,
	sellerId int64, userId int64) *UserMadeDeleteBasketOperation {
	return &UserMadeDeleteBasketOperation{
		ProductId: productId,
		SellerId: sellerId,
		UserId: userId,
		TopicName: topicName,
	}
}