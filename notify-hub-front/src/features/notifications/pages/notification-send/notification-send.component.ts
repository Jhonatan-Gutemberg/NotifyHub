import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../../../core/services/notification.service';
import { ToastData, ToastService } from '../../../../core/services/toast.service';
import { RouterOutlet } from "@angular/router";

@Component({
    selector: 'app-notification-send',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './notification-send.component.html',
    styleUrl: './notification-send.component.css'
})
export class NotificationSendComponent {
    nameFocused = false;
    emailFocused = false;
    formulario = {
        name: '',
        email: '',
        type: 'EMAIL',
        priority: 'LOW'
    };

    constructor(private service: NotificationService, private toast: ToastService) { }

    isSubmitting = false;
    sendData() {
        const cleanName = this.formulario.name.trim();
        const cleanEmail = this.formulario.email.toLowerCase().trim();
        const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;

        if (!emailPattern.test(cleanEmail)) {
            alert('Por favor, insira um e-mail real.');
            return;
        }

        this.isSubmitting = true;
        this.service.send(this.formulario).subscribe({
            next: (res) => {
                this.toast.show('Enviado com sucesso!', 'success');
                this.isSubmitting = false;
                this.resetForm();
            },
            error: (err) => {
                this.toast.show('Erro ao conectar com o servidor Java.', 'error');
            this.isSubmitting = false;
            }
        });
    }

    private resetForm() {
        this.formulario = { name: '', email: '', type: 'EMAIL', priority: 'LOW' };
    }
}