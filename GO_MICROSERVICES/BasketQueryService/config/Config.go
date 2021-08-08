package config

import (
	"fmt"
	"gopkg.in/yaml.v2"
	"os"
)

type Config struct {
	ElasticsearchConfig struct {
		Url string `yaml:"url"`
	} `yaml:"elasticsearch-config"`
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