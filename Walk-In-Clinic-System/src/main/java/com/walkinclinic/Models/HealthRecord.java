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
		private int idHealthRecord;
		
		@Column(name = "id_patient")
		private int idPatient;
		
		@Column(name = "id_visit")
		private int idVisit;
		
		@Column(name = "record_date")
		private String reocrdDate;
		
		@Column(name = "summary")
		private String summary;
		
		@Column(name = "notes")
		private String notes;
		
		@Column(name = "created_by")
		private int createdBy;

		public int getIdHealthRecord() {
			return idHealthRecord;
		}

		public void setIdHealthRecord(int idHealthRecord) {
			this.idHealthRecord = idHealthRecord;
		}

		public int getIdPatient() {
			return idPatient;
		}

		public void setIdPatient(int idPatient) {
			this.idPatient = idPatient;
		}

		public int getIdVisit() {
			return idVisit;
		}

		public void setIdVisit(int idVisit) {
			this.idVisit = idVisit;
		}

		public String getReocrdDate() {
			return reocrdDate;
		}

		public void setReocrdDate(String reocrdDate) {
			this.reocrdDate = reocrdDate;
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

		public int getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(int createdBy) {
			this.createdBy = createdBy;
		}

		@Override
		public String toString() {
			return "HealthRecord [idHealthRecord=" + idHealthRecord + ", idPatient=" + idPatient + ", idVisit="
					+ idVisit + ", reocrdDate=" + reocrdDate + ", summary=" + summary + ", notes=" + notes
					+ ", createdBy=" + createdBy + "]";
		}

		private HealthRecord(int idHealthRecord, int idPatient, int idVisit, String reocrdDate, String summary,
				String notes, int createdBy) {
			super();
			this.idHealthRecord = idHealthRecord;
			this.idPatient = idPatient;
			this.idVisit = idVisit;
			this.reocrdDate = reocrdDate;
			this.summary = summary;
			this.notes = notes;
			this.createdBy = createdBy;
		}

		private HealthRecord() {
			super();
			// TODO Auto-generated constructor stub
		}

		

		
}
