package models


type PromotionIsOver struct {
	UserList            []int64   `json:"userList"`
}

func NewPromotionIsOver (userList []int64, ) *PromotionIsOver {
	return &PromotionIsOver{
		UserList: userList,
	}
}