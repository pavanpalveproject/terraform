resource "google_compute_instance" "vm" {
  name                      = var.name
  machine_type              = var.machine
  zone                      = var.zone
  tags                      = [var.tags]


  boot_disk {
    initialize_params {
      image = var.disk_image
    }
  }

  network_interface {
    network = var.vpc_network
  }

}
