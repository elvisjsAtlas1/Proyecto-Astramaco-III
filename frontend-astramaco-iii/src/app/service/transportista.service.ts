import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class TransportistaService {

  private api = `${environment.apiUrl}/transportistas`;

  constructor(private http: HttpClient) {}

  crear(data: any) {
    return this.http.post(this.api, data);
  }

  listar() {
    return this.http.get(`${this.api}`);
  }

  listarPorTipo(tipo: string) {
    return this.http.get(`${this.api}/tipo/${tipo}`);
  }
}
