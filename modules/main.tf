resource "google_compute_instance" "vm" {
  name                      = var.name
  machine_type              = var.machine
  zone                      = var.zone
  tags                      = [var.tags]
  allow_stopping_for_update = var.allow_stopping_for_update
  deletion_protection       = var.deletion_protection

  boot_disk {
    initialize_params {
      image = var.disk_image
    }
  }

  network_interface {
    network = var.vpc_network
  }

 // scratch_disk {
   // interface = var.scratch_disk
  //}
  service_account {
    email  = google_service_account.default.email
    scopes = [var.scope]
  }
}
