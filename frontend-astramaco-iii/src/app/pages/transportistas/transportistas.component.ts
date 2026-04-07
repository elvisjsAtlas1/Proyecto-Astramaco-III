import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {TransportistaService} from '../../service/transportista.service';

@Component({
  selector: 'app-transportistas',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './transportistas.component.html'
})
export class TransportistasComponent {

  transportista = {
    nombre: '',
    apellidos: '',
    dni: '',
    edad: 0,
    tipoTransporte: 'CAMIONERO',
    placa: '',
    vehiculoInfo: '',
    capacidad: 0,
    estado: 'ACTIVO',
    usuarioId: 1
  };

  constructor(private service: TransportistaService) {}

  crear() {
    this.service.crear(this.transportista)
      .subscribe(() => alert('Transportista creado'));
  }

  transportistas: any[] = [];

  ngOnInit() {
    this.cargar();
  }

  cargar() {
    this.service.listar()
      .subscribe((data: any) => this.transportistas = data);
  }
}
