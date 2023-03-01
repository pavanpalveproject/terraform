
  module "compute_engine" {
  source                    = "../../modules"
  project_id                = "0-media-2"
  name                      = "dev-vm"
  region                    = "us-central1"
  zone                      = "us-central1-c"
  vpc_network               = "default"
  machine                   = "f1-micro"
  
  tags                      = "dev-server"
  disk_image                = "debian-cloud/debian-11"
  
 
  
}
