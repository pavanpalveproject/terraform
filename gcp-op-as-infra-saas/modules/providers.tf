provider "google" {
  credentials = file("/home/pavan_palve_project/o-media-2-59c02dc0d3cb.json")  
   project = var.project_id
  region  = var.region
  zone    = var.zone
}

# terraform {
#   required_providers {
#     google = {
#       source = "hashicorp/google"
#     }
#   }
# }

# provider "google" {
       
#   project     = "o-media-2"
#   region      = "asia-south1"
# }