package config

import (
	"fmt"
	"gopkg.in/yaml.v2"
	"os"
)

type Config struct {
	TopicNames struct {
		UserMadeAddBasketOperation string `yaml:"user-made-add-basket-operation"`
		UserMadeUpdateBasketOperation string `yaml:"user-made-update-basket-operation"`
		UserMadeDeleteBasketOperation string `yaml:"user-made-delete-basket-operation"`
		BasketUpdated string `yaml:"basket-updated"`
		PromotionInBasketChanged string `yaml:"promotion-in-basket-changed"`
		PromotionIsOver string `yaml:"promotion-is-over"`
		PromotionListChanged string `yaml:"promotion-list-changed"`
		MessageSplitter string `yaml:"message-splitter"`
	} `yaml:"topic-names"`
	ElasticsearchConfig struct {
		Url string `yaml:"url"`
	} `yaml:"elasticsearch-config"`
	KafkaConfig struct {
		Broker string `yaml:"broker"`
		GroupId string `yaml:"group-id"`
	} `yaml:"kafka-config"`
}

func ReadConfigVariable() *Config{
	var cfg Config
	readFile(&cfg)
	return &cfg
}

func processError(err error) {
	fmt.Println(err)
	os.Exit(2)
}

func readFile(cfg *Config) {
	f, err := os.Open("config.yml")
	if err != nil {
		processError(err)
	}
	defer f.Close()

	decoder := yaml.NewDecoder(f)
	err = decoder.Decode(cfg)
	if err != nil {
		processError(err)
	}
}