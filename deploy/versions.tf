terraform {
  required_version = ">= 1.4"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.63.0"
    }
  }

  backend "s3" {
    bucket  = "k-tech-feed-bucket"
    key     = "terraform/terraform.tfstate"
    region  = "ap-northeast-2"
    encrypt = true
    # dynamodb_table = "terraform-lock"
  }
}
