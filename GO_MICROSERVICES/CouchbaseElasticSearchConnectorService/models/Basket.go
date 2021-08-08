package models


type Basket struct {
	UserId               int64   	 `json:"userId"`
	Sellers			     []Seller    `json:"sellers"`
	Promotions			 []Promotion `json:"promotions"`
}

type Seller struct {
	SellerId             int64   	 `json:"sellerId"`
	Products 			 []Product   `json:"products"`
}

type Product struct {
	ProductId            int64   	  `json:"productId"`
	ProductTitle         string  	  `json:"productTitle"`
	ProductImageUrl      string  	  `json:"productImageUrl"`
	Price                float32      `json:"price"`
	Quantity             int          `json:"quantity"`
}

type Promotion struct {
	PromotionId          int64         `json:"promotionId"`
	PromotionTitle       string  	   `json:"promotionTitle"`
	DiscountPrice        float32  	   `json:"discountPrice"`
}

func NewBasket(id int64, sellers []Seller, promotions []Promotion) Basket {
	return Basket{
		UserId: id,
		Sellers: sellers,
		Promotions: promotions,
	}
}

func NewSeller(id int64, products []Product) Seller {
	return Seller{
		SellerId: id,
		Products: products,
	}
}

func NewProduct(id int64, title string, url string, price float32, quantity int) Product {
	return Product{
		ProductId: id,
		ProductTitle: title,
		ProductImageUrl: url,
		Price: price,
		Quantity: quantity,
	}
}

func NewPromotion(id int64, title string, price float32) Promotion {
	return Promotion{
		PromotionId: id,
		PromotionTitle: title,
		DiscountPrice: price,
	}
}

func CreateTestBasket() Basket {
	return Basket{
		UserId: 534,
		Sellers: []Seller{
			{
				SellerId: 1,
				Products: []Product{
					{
						ProductId: 1,
						ProductTitle: "bilgisayar",
						ProductImageUrl: "url",
						Price: 2,
						Quantity: 2,
					},
					{
						ProductId: 2,
						ProductTitle: "telefon",
						ProductImageUrl: "url",
						Price: 150,
						Quantity: 1,
					},
				},
			},
		},
		Promotions: []Promotion{
			{
				PromotionId: 1,
				PromotionTitle: "kargo",
				DiscountPrice: 9.9,
			},
		},
	}
}