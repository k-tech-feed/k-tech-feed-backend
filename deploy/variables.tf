variable "aws_region" {
  default = "ap-northeast-2"
}

variable "az_count" {
  default = "2"
}

# variable "source_path" {
#   description = "Path to the application to be built to a docker image"
#   default     = "../debit-credit-app"
# }

# variable "docker_image_tag" {
#   description = "Tag to use when pushing the docker image to the ecr repository"
#   default     = "latest"
# }

variable "app_port" {
  default = 8080
}

variable "domain" {
  default = "k-tech-feed.site"
}

variable "frontend_ip" {
  default = "76.76.21.21"
}

# variable "app_count" {
#   description = "Number of docker containers to run"
#   default     = 1
# }

# variable "ecs_task_execution_role" {
#   description = "Role arn for the ecsTaskExecutionRole"
#   default     = "arn:aws:iam::035898547283:role/ecsTaskExecutionRole"
# }

variable "ec2_key_name" {
  default = "ktechfeed"
}

variable "health_check_path" {
  default = "/health"
}

# variable "fargate_cpu" {
#   description = "Fargate instance CPU units to provision (1 vCPU = 1024 CPU units)"
#   default     = "1024"
# }

# variable "fargate_memory" {
#   description = "Fargate instance memory to provision (in MiB)"
#   default     = "2048"
# }

# Project name
variable "project_name" {
  default = "k-tech-feed"
}

# Project Repository
variable "project_repository" {
  default = "https://github.com/k-tech-feed/k-tech-feed-backend"
}

# User name for RDS
variable "db_username" {
  default = "user"
}

variable "db_password" {
  default = "letmein1234!!!!"
}

# The DB name in the RDS instance. Note that this cannot contain -'s
variable "db_name" {
  default = "ktechfeed"
}

variable "db_port" {
  default = "33306"
}

variable "db_instance_class" {
  default = "db.t2.micro"
}

variable "db_allocated_storage" {
  default = 10
}

variable "db_storage_type" {
  default = "gp2"
}

variable "db_engine" {
  default = "mysql"
}

variable "db_engine_version" {
  default = "8.0"
}

# variable "db_init_script" {
#   description = "SQL to initialise the RDS instance"
#   default     = "initial-schema.sql"
# }

variable "s3_bucket_name" {
  default = "k-tech-feed-bucket"
}
