# Ekwateur Billing API

## Description

Cette API gère la facturation pour les clients professionnels et individuels d'Ekwateur. Elle permet de calculer les montants de factures en fonction de la consommation d'électricité et de gaz, tout en respectant des exigences changeantes.

## Fonctionnalités

- **Calcul de la facture pour les clients professionnels**
- **Calcul de la facture pour les clients individuels**

## Endpoints

### 1. Calcul de la facture pour les clients professionnels

- **URL**: `/ekwateurbilling-api/calculate/pro`
- **Méthode**: `POST`
- **Paramètres**:
    - `kWhElectricity` (int): Consommation d'électricité en kWh
    - `kWhGas` (int): Consommation de gaz en kWh
    - `billingDate` (date): Date de facturation
- **Corps de la requête**:
  ```json
  {
    "clientReference": "EKW12345678",
    "siret": "12345678901234",
    "corporateName": "Entreprise XYZ",
    "proClientType": "HIGH_TURNOVER"
  }

### 1. Calcul de la facture pour les clients individuels

- **URL**: `/ekwateurbilling-api/calculate/individual`
- **Méthode**: `POST`
- **Paramètres**:
    - `kWhElectricity` (int): Consommation d'électricité en kWh
    - `kWhGas` (int): Consommation de gaz en kWh
    - `billingDate` (date): Date de facturation
- **Corps de la requête**:
  ```json
  {
    "clientReference": "EKW12345678",
    "civility": "M",
    "lastName": "Dupont",
    "firstName": "Jean"
  }