import {PedidoService} from '../../service/pedido.service';
import {TransportistaService} from '../../service/transportista.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {Component} from '@angular/core';
import {NgForOf} from '@angular/common';
import { OnInit } from '@angular/core';
@Component({
  selector: 'app-pedidos',
  standalone: true,
  imports: [FormsModule, NgForOf, CommonModule],
  templateUrl: './pedidos.component.html',
})
export class PedidosComponent {

  pedido = {
    clienteNombre: '',
    clienteTelefono: '',
    direccionEnvio: '',
    tipoTransporte: 'CAMIONERO',
    material: '',
    cantidad: 0,
    montoTotal: 0,
    adelanto: 0,
    piso: 1,
    horaEnvio: '',
    transportistaId: 0
  };

  transportistas: any[] = [];

  constructor(
    private pedidoService: PedidoService,
    private transportistaService: TransportistaService
  ) {}

  cargarTransportistas() {
    this.transportistaService
      .listarPorTipo(this.pedido.tipoTransporte)
      .subscribe((data: any) => this.transportistas = data);
  }

  crearPedido() {

    // 🔥 Asegurar formato correcto (opcional pero pro)
    if (this.pedido.horaEnvio && this.pedido.horaEnvio.length === 16) {
      this.pedido.horaEnvio += ':00';
    }

    this.pedidoService.crear(this.pedido)
      .subscribe(() => alert('Pedido creado'));
  }

  materiales: string[] = [];

  actualizarMateriales() {
    if (this.pedido.tipoTransporte === 'CAMIONERO') {
      this.materiales = ['PANDERETA', 'TECHO'];
    } else {
      this.materiales = [
        'ARENA_GRUESA',
        'ARENA_FINA',
        'ARENA_ASENTAR',
        'PIEDRA',
        'DESMONTE'
      ];
    }
  }
  pedidos: any[] = [];

  ngOnInit() {
    this.cargarPedidos();
  }

  cargarPedidos() {
    this.pedidoService.listar()
      .subscribe((data: any) => this.pedidos = data);
  }

}
