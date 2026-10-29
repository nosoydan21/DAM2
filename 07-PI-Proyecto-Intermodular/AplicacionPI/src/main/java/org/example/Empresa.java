    package org.example;

    public class Empresa {
        private int idEmpresa;
        private String nombre;
        private String localidad;
        private String provincia;
        private String sector;

        public Empresa(int idEmpresa, String nombre, String localidad, String provincia, String sector) {
            this.idEmpresa = idEmpresa;
            this.nombre = nombre;
            this.localidad = localidad;
            this.provincia = provincia;
            this.sector = sector;
        }

        public int getIdEmpresa() {
            return idEmpresa;
        }

        public void setIdEmpresa(int idEmpresa) {
            this.idEmpresa = idEmpresa;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

        public String getSector() {
            return sector;
        }

        public void setSector(String sector) {
            this.sector = sector;
        }
    }
