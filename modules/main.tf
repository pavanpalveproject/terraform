terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
    }
  }
}

# provider "google" {
#   credentials = file("/home/pavan_palve_project/o-media-2-59c02dc0d3cb.json")       #not_used
#   # credentials = $GOOGLE_APPLICATION_CREDENTIALS
#   project     = "o-media-2"
#   region      = "asia-south1"
# }

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
