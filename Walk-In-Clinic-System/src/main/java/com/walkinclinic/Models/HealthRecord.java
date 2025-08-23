package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "healthrecords")
public class HealthRecord {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_health_record")
		private int id_health_record;
		
		@Column(name = "id_patient")
		private int id_patient;
		
		@Column(name = "id_visit")
		private int id_visit;
		
		@Column(name = "record_date")
		private String record_date;
		
		@Column(name = "summary")
		private String summary;
		
		@Column(name = "notes")
		private String notes;
		
		@Column(name = "created_by")
		private int created_by;

		public int getId_health_record() {
			return id_health_record;
		}

		public void setId_health_record(int id_health_record) {
			this.id_health_record = id_health_record;
		}

		public int getId_patient() {
			return id_patient;
		}

		public void setId_patient(int id_patient) {
			this.id_patient = id_patient;
		}

		public int getId_visit() {
			return id_visit;
		}

		public void setId_visit(int id_visit) {
			this.id_visit = id_visit;
		}

		public String getRecord_date() {
			return record_date;
		}

		public void setRecord_date(String record_date) {
			this.record_date = record_date;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public int getCreated_by() {
			return created_by;
		}

		public void setCreated_by(int created_by) {
			this.created_by = created_by;
		}

		@Override
		public String toString() {
			return "HealthRecord [id_health_record=" + id_health_record + ", id_patient=" + id_patient + ", id_visit="
					+ id_visit + ", record_date=" + record_date + ", summary=" + summary + ", notes=" + notes
					+ ", created_by=" + created_by + "]";
		}

		private HealthRecord(int id_health_record, int id_patient, int id_visit, String record_date, String summary,
				String notes, int created_by) {
			super();
			this.id_health_record = id_health_record;
			this.id_patient = id_patient;
			this.id_visit = id_visit;
			this.record_date = record_date;
			this.summary = summary;
			this.notes = notes;
			this.created_by = created_by;
		}

		private HealthRecord() {
			super();
			// TODO Auto-generated constructor stub
		}
}
