export class Patient{
    ssn!: number;
    name!:string;
    address!:string;
    phone!: number;
    insuranceID!:number;
    pcp!:number;
    


constructor(){
    this.ssn=0;
    this.name='';
    this.address='';
    this.phone=0;
    this.insuranceID=0;
    this.pcp=0;
    
}
}
