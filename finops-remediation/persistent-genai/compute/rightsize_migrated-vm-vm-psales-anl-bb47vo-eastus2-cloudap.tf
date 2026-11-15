# FinOps-as-a-Service Governed Compute Rightsizing Patch (UC3 / UC6)
# Snapshot-first & reversible machine_type transition for `migrated-vm-vm-psales-anl-bb47vo-eastus2-cloudapp-azure-com`
resource "google_compute_disk_snapshot" "pre_rightsize_migrated_vm_vm_psales_anl_bb47vo_eastus2_cloudap" {
  name        = "pre-rightsize-migrated-vm-vm-psales-anl-bb47vo"
  project     = "persistent-genai"
  source_disk = "migrated-vm-vm-psales-anl-bb47vo-eastus2-cloudapp-azure-com"
  zone        = "us-central1-a"
}

# Update target instance `migrated-vm-vm-psales-anl-bb47vo-eastus2-cloudapp-azure-com` in `us-central1-a` -> `e2-standard-2`
locals {
  target_instance          = "migrated-vm-vm-psales-anl-bb47vo-eastus2-cloudapp-azure-com"
  recommended_machine_type = "e2-standard-2"
}
