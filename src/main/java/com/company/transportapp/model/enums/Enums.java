package com.company.transportapp.model.enums;

public class Enums {

    public enum TransportType { CONTAINER, REEFER, TANK }  // TANK = cisterna
    public enum Direction { IMPORT, EXPORT, DOMESTIC }  // DOMESTIC = vnitrostátní
    public enum StopType { LOADING, UNLOADING }
    public enum DocumentType { CMR, T1, INTERCHANGE, OTHER }
}
