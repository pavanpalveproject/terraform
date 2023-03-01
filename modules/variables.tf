variable "project_id" {
  description = "The GCP project to use for integration tests"
  type        = string
}
variable "region" {
  description = "The GCP region to create and test resources in"
  type        = string
  default     = "us-central1"
}
variable "zone" {
  description = "The GCP zone to create resources in"
  type        = string
  default     = "us-central1-a"
}
variable "machine" {
  description = "VM machine"
  type        = string
  default     = "f1-micro"
}
variable "name" {
  description = "The GCP vm name to create resources in"
  type        = string
}
variable "disk_image" {
  type        = string
  description = "Enter the Image name of the disk"
  default     = "debian-cloud/debian-11"
}
variable "vpc_network" {
  type        = string
  description = "Enter the VPC name"
}
variable "tags" {
  type        = string
  description = "Enter the VPC name"
}


