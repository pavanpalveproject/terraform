terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
    }
  }
}

provider "google" {
  credentials = file("/home/pavan_palve_project/o-media-2-59c02dc0d3cb.json")       
  project     = "o-media-2"
  region      = "asia-south1"
}

module "compute_engine" {
  source                    = "../modules"
  project_id                = var.project_id
  name                      = var.name
  region                    = var.region
  zone                      = var.zone
  vpc_network               = var.vpc_network
  machine                   = var.machine
  tags                      = var.tags
  disk_image                = var.disk_image
}