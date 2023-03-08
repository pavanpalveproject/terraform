# terraform {
#   required_providers {
#     google = {
#       source = "hashicorp/google"
#     }
#   }
# }

# provider "google" {
#   credentials = file("/home/pavan_palve_project/o-media-2-59c02dc0d3cb.json")       
#   project     = "o-media-2"
#   region      = "asia-south1"
# }

module "compute_engine" {
  source                    = "../../../modules"
  project_id                = "o-media-2"
  name                      = var.name
  region                    = "us-central1"
  zone                      = "us-central1-c"
  vpc_network               = "default"
  machine                   = var.machine
  tags                      = "dev-server"
  disk_image                = "debian-cloud/debian-11"
}

# module "vpc_network"{

#     source = "git::https://github.com/maniksde/TerraformModule.git"

#     project = "o-media-practice"

#     region = "us-central1"

#     vpc_network = "manik-repo-practice-vpc"

#     auto_create_subnetworks = false

# }