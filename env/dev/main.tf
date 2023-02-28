terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
    }
  }
}

# resource "google_secret_manager_secret_version" "credentials" {
#   provider = google-beta

#   secret = "projects/o-media-2/secrets/key-jenkins/versions/latest"
# }

# data "google_secret_manager_secret_version" "service_account_key" {
#   provider = google-beta

#   secret = "projects/o-media-2/secrets/key-jenkins"
# }

# data "google_secret_manager_secret_version" "key" {
#   provider = google-beta
#   secret   = "admin-username"
# }


provider "google" {
  credentials = file("/home/pavan_palve_project/o-media-2-59c02dc0d3cb.json")       #not_used
  # credentials = $GOOGLE_APPLICATION_CREDENTIALS
  project     = "o-media-2"
  region      = "asia-south1"
}


resource "google_compute_instance" "my-cicd-vm" {
  name         = "cicd-vms"
  machine_type = "e2-small"
  project = "o-media-2"
  zone     = "asia-south1-a"

 

  tags = ["allow-firewall"]

 

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-11"
      labels = {
        my_label = "value"
      }
    }
  }

 

  network_interface {
    network = "default"

 

  }
  allow_stopping_for_update = true
}

# resource "google_storage_bucket" "auto-1" {
#   name          = "auto-expiring-bucket123bbbcvbdfgq"
#   location      = "US"
#   force_destroy = true

#   lifecycle_rule {
#     condition {
#       age = 3
#     }
#     action {
#       type = "Delete"
#     }
#   }

#   lifecycle_rule {
#     condition {
#       age = 1
#     }
#     action {
#       type = "AbortIncompleteMultipartUpload"
#     }
#   }
# }
# resource "google_storage_bucket" "auto-2" {
#   name          = "auto-expiring-bucket123bbbcvbdfgq11"
#   location      = "US"
#   force_destroy = true

#   lifecycle_rule {
#     condition {
#       age = 3
#     }
#     action {
#       type = "Delete"
#     }
#   }

#   lifecycle_rule {
#     condition {
#       age = 1
#     }
#     action {
#       type = "AbortIncompleteMultipartUpload"
#     }
#   }
# }
