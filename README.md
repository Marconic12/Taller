# Documentación del Sistema de Gestión de Gimnasio

Este sistema automatiza la administración de un centro deportivo, gestionando desde el control de acceso por niveles de suscripción hasta la logística de cupos en clases grupales.

## ⚙️ ¿Qué hace cada clase?

### 1. Gestión Administrativa (Gimnasio)
Es el contenedor principal. Su función es centralizar la base de datos de los socios y gestionar el flujo de usuarios. Permite auditar quiénes tienen acceso gratuito o premium para la toma de decisiones comerciales.

### 2. Control de Socios (Usuario)
Almacena la identidad digital del cliente. Su rol es servir de puente entre el gimnasio y los servicios (clases y membresías). Es el objeto que "posee" el derecho de uso de las instalaciones.

### 3. Logística de Actividades (Clase)
Controla la oferta deportiva. Su lógica principal es la validación de disponibilidad: asegura que ningún usuario se inscriba si el `cupoMaximo` ha sido alcanzado, organizando así los horarios del centro.

### 4. Modelo de Negocio (Membresía + Herencia)
Aquí reside la lógica de permisos. El sistema utiliza **Herencia** para diferenciar niveles:
* **Membresía:** Define si el usuario está activo o vencido.
* **Gratis:** Limita la cantidad de clases.
* **Premium:** Habilita servicios VIP (entrenador y áreas exclusivas).

### 5. Control Financiero (Pago)
Garantiza la trazabilidad del dinero. Registra cada transacción vinculada a una membresía para validar que el servicio esté pago.

---

## 🔗 Relaciones: ¿Cómo se conectan?

El sistema funciona mediante una estructura de dependencias jerárquicas:

1.  **Gimnasio ↔ Usuario ($0..*$):** Un gimnasio es una colección de usuarios. Sin gimnasio no hay registro, y el gimnasio existe para administrar a esos usuarios.
2.  **Usuario ↔ Membresía ($1:1$):** Cada usuario tiene asignado obligatoriamente un plan. La membresía es la que dicta qué puede o no hacer el usuario dentro del sistema.
3.  **Usuario ↔ Clase ($1..*$):** Es una relación de acción. El usuario se "anota" en la clase, y la clase actualiza su lista interna de asistentes para controlar el aforo.
4.  **Membresía ↔ Pago ($1..*$):** Una membresía requiere de pagos para existir o renovarse. La relación permite ver el historial de pagos de un socio específico.
5.  **Herencia (Membresía):** `MembresiaGratis` y `MembresiaPremium` **son** un tipo de Membresía. Esto permite que el sistema trate a ambas por igual al verificar si están activas, pero de forma distinta al otorgar beneficios.
