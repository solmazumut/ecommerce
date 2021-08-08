package models

type UserMadeUpdateBasketOperation struct {
	ProductId            int64   `json:"productId"`
	ProductTitle         string  `json:"productTitle"`
	ProductImageUrl      string  `json:"productImageUrl"`
	SellerId             int64   `json:"sellerId"`
	Price                float32 `json:"price"`
	Stock                int     `json:"stock"`
	UserId               int64   `json:"userId"`
	Quantity             int     `json:"quantity"`
	TopicName            string  `json:"topicName"`
}

func TransformToUserMadeAddBasketOperation(updateOperation UserMadeUpdateBasketOperation) UserMadeAddBasketOperation{
	addOperation := NewUserMadeAddBasketOperation(updateOperation.ProductId, updateOperation.ProductTitle,
		updateOperation.ProductImageUrl, updateOperation.SellerId, updateOperation.Price, updateOperation.TopicName,
	updateOperation.Quantity, updateOperation.UserId)
	return addOperation
}

func NewUserMadeUpdateBasketOperation (productId int64, productTitle string,
	productImageUrl string, sellerId int64, price float32, topicName string,
	userId int64, quantity int) UserMadeUpdateBasketOperation {
	return UserMadeUpdateBasketOperation{
		ProductId: productId,
		ProductTitle: productTitle,
		ProductImageUrl: productImageUrl,
		SellerId: sellerId,
		Price: price,
		UserId: userId,
		Quantity: quantity,
		TopicName: topicName,
	}
}
