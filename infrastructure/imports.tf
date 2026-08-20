# The APIM product, API and policy already exist in sbox, created outside this
# state. These import blocks adopt them so the first apply does not conflict.
# Remove them once the resources are recorded in state.

locals {
  apim_base = "/subscriptions/bd2864ed-4f3e-45ed-9c6a-8d179674bab1/resourceGroups/rg-sps-platform-sbox/providers/Microsoft.ApiManagement/service/sps-api-mgmt-sbox"
}

import {
  to = module.product.azurerm_api_management_product.product
  id = "${local.apim_base}/products/cp-crime-defendant-details"
}

import {
  to = module.product.azurerm_api_management_product_group.access_control_groups["administrators"]
  id = "${local.apim_base}/products/cp-crime-defendant-details/groups/administrators"
}

import {
  to = module.product.azurerm_api_management_product_group.access_control_groups["developers"]
  id = "${local.apim_base}/products/cp-crime-defendant-details/groups/developers"
}

import {
  to = module.product.azurerm_api_management_product_group.access_control_groups["guests"]
  id = "${local.apim_base}/products/cp-crime-defendant-details/groups/guests"
}

import {
  to = module.apis["defendantdetails"].azurerm_api_management_api.api
  id = "${local.apim_base}/apis/crime-defendant-details-api;rev=1"
}

import {
  to = module.apis["defendantdetails"].azurerm_api_management_product_api.link_to_product[0]
  id = "${local.apim_base}/products/cp-crime-defendant-details/apis/crime-defendant-details-api"
}

import {
  to = azurerm_api_management_api_policy.api_policy["defendantdetails"]
  id = "${local.apim_base}/apis/crime-defendant-details-api"
}
