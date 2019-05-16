package com.weather.demo.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection ="metric")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonFilter("idfilter")

public class Metric {
        @Id
        private UUID id;
        private String name;
        private String unit;
        private Boolean enabled;

        public Metric() {
        }

        public Metric(UUID id, String name, String unit, Boolean enabled) {
                this.id = id;
                this.name = name;
                this.unit = unit;
                this.enabled = enabled;
        }

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getUnit() {
                return unit;
        }

        public void setUnit(String unit) {
                this.unit = unit;
        }

        public Boolean getEnabled() {
                return enabled;
        }

        public void setEnabled(Boolean enabled) {
                this.enabled = enabled;
        }

        @Override
        public String toString() {
                return "Metric{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", unit='" + unit + '\'' +
                        ", enabled=" + enabled +
                        '}';
        }
}
