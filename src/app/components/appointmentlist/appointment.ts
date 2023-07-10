export class Appointment{
    appointmentId!: number;
    patient!:number;
   startDateTime!: DateConstructor; 
    prepNurse!: number;
    physician!: number
    endDateTime!: DateConstructor
    examinationRoom!: number


constructor(){
    this.appointmentId=0;
    this.patient=0;
    this.prepNurse=0;
    this.physician=0;
    this.startDateTime= Date;
    this.endDateTime= Date;
   this.examinationRoom=0;

}
}