package com.hms.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	List<Appointment> findAppointmentByStartDateTime(LocalDateTime startDateTime);
	Optional<Appointment> findPatientByAppointmentId(Integer appointmentId);
	Optional<Appointment> findPhysicianByAppointmentId(Integer appointmentId);
	Optional<Appointment> findNurseByAppointmentId(Integer appointmentId);
	List<Appointment> findPhysicianByPatientSsn(Integer patientId);
	Optional<Appointment> findPatientByStartDateTime (LocalDateTime startDateTime);
	Optional<Appointment> findPhysicianByPatientSsnAndStartDateTime(Integer patientId, LocalDateTime startDateTime);
	List<Appointment> findNurseByPatientSsn(Integer patientId);
	Optional<Appointment> findNurseByPatientSsnAndStartDateTime(Integer patientId, LocalDateTime startDateTime);
	List<Appointment> findStartDateTimeByPatientSsn(Integer patientId);
	List<Appointment> findPatientByPhysicianEmployeeId(Integer physicianId);
	List<Appointment> findPatientByPhysicianEmployeeIdAndStartDateTime(Integer physicianID, LocalDateTime startDateTime);
	Optional<Appointment> findPatientByPhysicianEmployeeIdAndPatientSsn(Integer physicianId, Integer patientId);
	List<Appointment> findPatientByPrepNurseEmployeeId(Integer nurseId);
	Optional<Appointment> findPatientByPrepNurseEmployeeIdAndPatientSsn(Integer nurseId, Integer patientId);
	List<Appointment> findPatientByPrepNurseEmployeeIdAndStartDateTime(Integer nurseId, LocalDateTime startDateTime);
	Optional<Appointment> findRoomByPatientSsnAndStartDateTime(Integer patientId, LocalDateTime startDateTime);
	List<Appointment> findRoomByPhysicianEmployeeIdAndStartDateTime(Integer physicianId, LocalDateTime startDateTime);
	List<Appointment> findRoomByPrepNurseEmployeeIdAndStartDateTime(Integer nurseId, LocalDateTime startDateTime);
	Optional<Appointment> findRoomByAppointmentId(Integer appointmentId);
	

	
	
}
