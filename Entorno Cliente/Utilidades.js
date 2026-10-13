/*
 * ==========================================================
 * UTILIDADES.JS
 * Funciones reutilizables para ejercicios de JavaScript,
 * DOM y AJAX.
 * ==========================================================
 */


/* ==========================================================
   1. VALIDAR NOMBRE
   ========================================================== */

function validarNombre() {

    // Obtenemos el nombre y eliminamos espacios exteriores.
    const nombre = document.getElementById("nombre").value.trim();

    // Obtenemos el elemento donde mostraremos el error.
    const info = document.getElementById("info_nombre");

    // Comprobamos que el campo no esté vacío.
    if (nombre === "") {
        info.textContent = "El nombre es obligatorio.";
        return false;
    }

    // Comprobamos que tenga al menos 2 caracteres.
    if (nombre.length < 2) {
        info.textContent = "El nombre debe tener al menos 2 letras.";
        return false;
    }

    // Permitimos letras españolas y espacios.
    const expresion = /^[A-Za-zÁÉÍÓÚáéíóúÑñÜü\s]+$/;

    // Comprobamos que todo el contenido cumpla la expresión regular.
    if (!expresion.test(nombre)) {
        info.textContent = "El nombre solo puede contener letras y espacios.";
        return false;
    }

    // Si es válido, borramos cualquier mensaje anterior.
    info.textContent = "";

    // Indicamos que la validación ha sido correcta.
    return true;
}


/* ==========================================================
   2. VALIDAR APELLIDOS
   ========================================================== */

function validarApellido() {

    // Leemos los apellidos y eliminamos espacios exteriores.
    const apellido = document.getElementById("apellidos").value.trim();

    // Elemento donde aparecerá el mensaje.
    const info = document.getElementById("info_apellidos");

    // Comprobamos que no esté vacío.
    if (apellido === "") {
        info.textContent = "Los apellidos son obligatorios.";
        return false;
    }

    // Exigimos un mínimo de 4 caracteres.
    if (apellido.length < 4) {
        info.textContent = "Los apellidos deben tener al menos 4 caracteres.";
        return false;
    }

    // Permitimos letras españolas y espacios.
    const expresion = /^[A-Za-zÁÉÍÓÚáéíóúÑñÜü\s]+$/;

    // Rechazamos números y símbolos.
    if (!expresion.test(apellido)) {
        info.textContent = "Los apellidos solo pueden contener letras y espacios.";
        return false;
    }

    // Borramos el mensaje si todo es correcto.
    info.textContent = "";

    return true;
}


/* ==========================================================
   3. VALIDAR DNI
   Comprueba el formato y que la letra sea correcta.
   ========================================================== */

function validarDNI() {

    // Leemos el DNI, eliminamos espacios y ponemos la letra en mayúscula.
    const dni = document.getElementById("dni").value.trim().toUpperCase();

    // Elemento donde mostraremos el error.
    const info = document.getElementById("info_dni");

    // Comprobamos que tenga 8 números y una letra.
    if (!/^[0-9]{8}[A-Z]$/.test(dni)) {
        info.textContent = "El DNI debe tener 8 números y una letra.";
        return false;
    }

    // Letras utilizadas para calcular la letra correcta del DNI.
    const letras = "TRWAGMYFPDXBNJZSQVHLCKE";

    // Extraemos los ocho primeros caracteres y los convertimos en número.
    const numero = Number(dni.substring(0, 8));

    // Obtenemos la letra escrita en el DNI.
    const letra = dni.charAt(8);

    // El DNI es válido.
    info.textContent = "";

    return true;
}


/* ==========================================================
   4. VALIDAR SEXO TIPO DESPLEGABLE
   ========================================================== */

function validarSexoDesplegable() {

    // Obtenemos el valor seleccionado.
    const sexo = document.getElementById("sexo").value;

    // Elemento para mostrar el error.
    const info = document.getElementById("info_sexo");

    // Si el valor está vacío, no se ha seleccionado una opción.
    if (sexo === "") {
        info.textContent = "Debes seleccionar una opción.";
        return false;
    }

    // Limpiamos el mensaje anterior.
    info.textContent = "";

    return true;
}

/* ==========================================================
   4. VALIDAR SEXO TIPO RADIO BUTTON
   ========================================================== */

function validarSexoRadio() {
			const sexo = document.getElementsByName("sexo");
			const info = document.getElementById("info_sexo");

			let seleccionado = false;

			for (let i = 0; i < sexo.length; i++) {
				if (sexo[i].checked) {
					seleccionado = true;
					break;
				}
			}

			if (!seleccionado) {
				info.textContent = "Debes seleccionar una opción.";
				return false;
			}

			return true;
		}

/* ==========================================================
   5. VALIDAR SUGERENCIA
   ========================================================== */

function validarSugerencia() {

    // Leemos el texto y quitamos espacios exteriores.
    const sugerencia = document.getElementById("sugerencia").value.trim();

    // Elemento donde mostraremos el error.
    const info = document.getElementById("info_sugerencia");

    // Comprobamos que la sugerencia no esté vacía.
    if (sugerencia === "") {
        info.textContent = "La sugerencia es obligatoria.";
        return false;
    }

    // Borramos el mensaje de error.
    info.textContent = "";

    return true;
}


/* ==========================================================
   6. VALIDAR FECHA
   Los campos son f_dia, f_mes y f_ano.
   ========================================================== */

function validarFecha() {

    // Leemos los tres campos como texto.
    const f_dia = document.getElementById("f_dia").value.trim();
    const f_mes = document.getElementById("f_mes").value.trim();
    const f_ano = document.getElementById("f_ano").value.trim();

    // Elemento donde mostraremos el error.
    const info_fecha = document.getElementById("info_fecha");

    // Comprobamos que los tres campos estén rellenos.
    if (f_dia === "" || f_mes === "" || f_ano === "") {
        info_fecha.textContent = "La fecha es obligatoria.";
        return false;
    }

    // Comprobamos que solo contengan números enteros.
    if (!/^\d+$/.test(f_dia) ||
        !/^\d+$/.test(f_mes) ||
        !/^\d+$/.test(f_ano)) {

        info_fecha.textContent = "La fecha debe contener números enteros.";
        return false;
    }

    // Convertimos los valores a números.
    const dia = Number(f_dia);
    const mes = Number(f_mes);
    const ano = Number(f_ano);

    // Comprobamos que el año tenga cuatro cifras.
    if (f_ano.length !== 4) {
        info_fecha.textContent = "El año debe tener 4 cifras.";
        return false;
    }

    // Comprobamos el intervalo del día.
    if (dia < 1 || dia > 31) {
        info_fecha.textContent = "El día debe estar entre 1 y 31.";
        return false;
    }

    // Comprobamos el intervalo del mes.
    if (mes < 1 || mes > 12) {
        info_fecha.textContent = "El mes debe estar entre 1 y 12.";
        return false;
    }

    // Creamos el objeto Date.
    // En JavaScript los meses empiezan en 0: enero es 0.
    const fecha = new Date(ano, mes - 1, dia);

    // Comprobamos que JavaScript no haya cambiado el día, mes o año.
    // Esto detecta fechas como 31/02/2025.
    if (fecha.getDate() !== dia ||
        fecha.getMonth() !== mes - 1 ||
        fecha.getFullYear() !== ano) {

        info_fecha.textContent = "La fecha no es válida.";
        return false;
    }

    // La fecha existe y es válida.
    info_fecha.textContent = "";

    return true;
}


/* ==========================================================
   7. VALIDAR ESTATURA
   Admite valores entre 0.50 y 2.50 con dos decimales.
   ========================================================== */

function validarEstatura() {

    // Leemos el campo y quitamos espacios exteriores.
    const estatura = document.getElementById("estatura").value.trim();

    // Elemento para mostrar el mensaje.
    const info = document.getElementById("info_estatura");

    // Comprobamos que no esté vacío.
    if (estatura === "") {
        info.textContent = "La estatura es obligatoria.";
        return false;
    }

    // Expresión regular para aceptar desde 0.50 hasta 2.50.
    const expresion =
        /^(0\.(5[0-9]|[6-9][0-9])|1\.[0-9]{2}|2\.[0-4][0-9]|2\.50)$/;

    // Comprobamos el formato y el intervalo.
    if (!expresion.test(estatura)) {
        info.textContent =
            "La estatura debe estar entre 0.50 y 2.50 metros, con dos decimales.";
        return false;
    }

    // La estatura es válida.
    info.textContent = "";

    return true;
}


/* ==========================================================
   8. VALIDAR ESTADO CIVIL
   ========================================================== */

function validarEstadoCivil() {

    // Leemos el valor seleccionado.
    const estadoCivil = document.getElementById("estado_civil").value;

    // Elemento para mostrar el mensaje.
    const info = document.getElementById("info_estado_civil");

    // Comprobamos que se haya elegido una opción.
    if (estadoCivil === "") {
        info.textContent = "Debes seleccionar una opción.";
        return false;
    }

    // Borramos el mensaje anterior.
    info.textContent = "";

    return true;
}


/* ==========================================================
   9. VALIDAR NÚMERO DE CUENTA
   Comprueba que haya exactamente 20 dígitos.
   ========================================================== */

function validarNumCuenta() {

    // IMPORTANTE: value es una propiedad, no se escribe value().
    const numCuenta = document.getElementById("ccc").value.trim();

    // Elemento donde mostraremos el error.
    const info = document.getElementById("info_ccc");

    // Comprobamos que el campo esté relleno.
    if (numCuenta === "") {
        info.textContent = "El número de cuenta es obligatorio.";
        return false;
    }

    // Exigimos exactamente 20 dígitos.
    // Esta comprobación rechaza letras, espacios y símbolos.
    if (!/^[0-9]{20}$/.test(numCuenta)) {
        info.textContent =
            "El número de cuenta debe contener exactamente 20 dígitos.";
        return false;
    }

    // El formato es correcto.
    // No se comprueba aquí el dígito de control de la cuenta.
    info.textContent = "";

    return true;
}


/* ==========================================================
   10. VALIDAR BEBIDAS
   Exige marcar al menos tres casillas con name="bebidas".
   ========================================================== */

function validarCerveza() {

    // Obtenemos todos los elementos cuyo name sea "bebidas".
    const bebidas = document.getElementsByName("bebidas");

    // Elemento donde mostraremos el error.
    const info = document.getElementById("info_bebidas");

    // Inicializamos el contador de casillas marcadas.
    let cantidad = 0;

    // Recorremos todas las casillas.
    for (let i = 0; i < bebidas.length; i++) {

        // Comprobamos si la casilla actual está marcada.
        if (bebidas[i].checked) {

            // Si está marcada, aumentamos el contador.
            cantidad++;
        }
    }

    // Exigimos un mínimo de tres bebidas.
    if (cantidad < 3) {
        info.textContent = "Hay que marcar como mínimo 3 bebidas.";
        return false;
    }

    // Se cumple el mínimo.
    info.textContent = "";

    return true;
}

/* ==========================================================
   11. VALIDAR CORREO ELECTRÓNICO
   ========================================================== */

function validarEmail(idCampo, idInfo) {

    // Leemos el correo usando el identificador recibido.
    const email = document.getElementById(idCampo).value.trim();

    // Obtenemos el elemento donde se mostrará el error.
    const info = document.getElementById(idInfo);

    // Comprobamos que el correo no esté vacío.
    if (email === "") {
        info.textContent = "El correo electrónico es obligatorio.";
        return false;
    }

    // Comprobamos el formato básico del correo.
    const expresion = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    // Si el correo no coincide con el formato, mostramos el error.
    if (!expresion.test(email)) {
        info.textContent = "El correo electrónico no es válido.";
        return false;
    }

    // Borramos el error si el correo es válido.
    info.textContent = "";

    return true;
}


/* ==========================================================
   12. VALIDAR NÚMERO ENTERO
   ========================================================== */

function validarEntero(idCampo, idInfo) {

    // Leemos el valor del campo y quitamos espacios exteriores.
    const valor = document.getElementById(idCampo).value.trim();

    // Elemento donde mostraremos el error.
    const info = document.getElementById(idInfo);

    // Comprobamos si está vacío.
    if (valor === "") {
        info.textContent = "El campo es obligatorio.";
        return false;
    }

    // Aceptamos enteros positivos, negativos y el cero.
    // Ejemplos válidos: 5, 0, -3.
    // No acepta decimales como 2.5.
    if (!/^-?\d+$/.test(valor)) {
        info.textContent = "Debes introducir un número entero.";
        return false;
    }

    // El valor es válido.
    info.textContent = "";

    return true;
}


/* ==========================================================
   13. VALIDAR UNA LISTA DESPLEGABLE GENÉRICA
   ========================================================== */

function validarSelect(idCampo, idInfo) {

    // Obtenemos el valor seleccionado.
    const valor = document.getElementById(idCampo).value;

    // Obtenemos el elemento para los mensajes.
    const info = document.getElementById(idInfo);

    // La opción inicial debe tener value="".
    if (valor === "") {
        info.textContent = "Debes seleccionar una opción.";
        return false;
    }

    // Si hay una opción seleccionada, borramos el error.
    info.textContent = "";

    return true;
}


/* ==========================================================
   14. LIMPIAR UN MENSAJE DE ERROR
   ========================================================== */

function limpiarMensaje(idInfo) {

    // Buscamos el elemento que contiene el mensaje.
    const info = document.getElementById(idInfo);

    // Comprobamos que el elemento exista.
    if (info !== null) {

        // Eliminamos su texto.
        info.textContent = "";
    }
}


/* ==========================================================
   15. LIMPIAR TODOS LOS MENSAJES DE ERROR
   Los mensajes deben tener class="error".
   ========================================================== */

function limpiarMensajes() {

    // Seleccionamos todos los elementos que tengan la clase error.
    const mensajes = document.querySelectorAll(".error");

    // Recorremos los elementos encontrados.
    for (let i = 0; i < mensajes.length; i++) {

        // Vaciamos el contenido de cada elemento.
        mensajes[i].textContent = "";
    }
}

/* ==========================================================
   16. PETICIÓN AJAX GET REUTILIZABLE
   ========================================================== */

function peticionAJAX(url, funcionCorrecta, funcionError) {

    // Creamos el objeto que permite realizar peticiones AJAX.
    const xhttp = new XMLHttpRequest();

    // Esta función se ejecuta cuando cambia el estado de la petición.
    xhttp.onreadystatechange = function () {

        // readyState === 4 significa que la petición ha terminado.
        if (xhttp.readyState === 4) {

            // status === 200 significa que la respuesta HTTP ha sido correcta.
            if (xhttp.status === 200) {

                // Comprobamos que se haya indicado una función de éxito.
                if (typeof funcionCorrecta === "function") {

                    // Ejecutamos la función y le pasamos la respuesta del servidor.
                    funcionCorrecta(xhttp.responseText);
                }

            } else {

                // Creamos un mensaje con el código de error HTTP.
                const mensaje =
                    "Error en la petición AJAX. Código: " + xhttp.status;

                // Si existe una función para gestionar errores, la ejecutamos.
                if (typeof funcionError === "function") {

                    // Pasamos el mensaje y el código de estado.
                    funcionError(mensaje, xhttp.status);

                } else {

                    // Si no hay función de error, mostramos el problema en consola.
                    console.error(mensaje);
                }
            }
        }
    };

    // Indicamos el método GET, la URL y que la petición sea asíncrona.
    xhttp.open("GET", url, true);

    // Enviamos la petición al servidor.
    xhttp.send();
}

/* ==========================================================
   17. FUNCION PARA GENERAR UNA TABLA REUSABLE
   ========================================================== */

// FUNCIÓN QUE CREA LA TABLA DINÁMICAMENTE
function Generar_Tabla() {

    // Vacía el contenedor antes de crear una tabla nueva.
    // Así, si pulsamos el botón varias veces, no se acumulan tablas.
    document.getElementById("tabla").innerHTML = "";

    // Obtiene los valores introducidos por el usuario en los inputs.
    // .value devuelve el contenido como texto.
    const filas = document.getElementById("nfilas").value;
    const columnas = document.getElementById("ncolumnas").value;

    // Crea un elemento HTML <table>.
    // Todavía no está insertado en el documento.
    const tabla = document.createElement("table");

    // Crea los elementos que separan la cabecera del cuerpo.
    const thead = document.createElement("thead");
    const tbody = document.createElement("tbody");

    // Introduce thead y tbody dentro de la tabla.
    tabla.appendChild(thead);
    tabla.appendChild(tbody);

    // Introduce la tabla dentro del div con id="tabla".
    document.getElementById("tabla").appendChild(tabla);


    // BUCLE EXTERIOR: CREA LAS FILAS.
    // Empieza en 0 y se repite tantas veces como filas se hayan indicado.
    for (let i = 0; i < filas; i++) {

        // Crea una fila HTML <tr>.
        const fila = document.createElement("tr");

        // Si i vale 0, estamos en la primera fila.
        // Por tanto, la añadimos a thead.
        if (i == 0) {
            thead.appendChild(fila);
        } else {
            // Las demás filas se añaden a tbody.
            tbody.appendChild(fila);
        }


        // BUCLE INTERIOR: CREA LAS CELDAS DE CADA FILA.
        // Se ejecuta tantas veces como columnas se hayan indicado.
        for (let j = 0; j < columnas; j++) {

            // Declaramos la variable fuera del if para poder
            // utilizarla después, independientemente de la rama.
            let columna;

            // Si estamos en la primera fila, creamos una cabecera <th>.
            if (i == 0) {
                columna = document.createElement("th");

                // Añade texto a la cabecera.
                // j + 1 hace que la numeración empiece en 1.
                columna.textContent = "Cabecera " + (j + 1);

            } else {
                // En las demás filas creamos celdas normales <td>.
                columna = document.createElement("td");

                // Escribe el número de columna y de fila.
                // j + 1 e i + 1 evitan empezar la numeración en 0.
                columna.textContent =
                    "Columna " + (j + 1) + ", Fila " + (i + 1);
            }

            // Introduce la celda dentro de la fila correspondiente.
            fila.appendChild(columna);

            // Añade un borde a cada celda.
            columna.style.border = "1px solid black";

            // Asigna un evento de clic a la celda.
            // La función devuelve otra función que conserva
            // los valores actuales de i y j.
            columna.onclick = crearManejador(i, j);
        }
    }

    // Añade un borde exterior a toda la tabla.
    tabla.style.border = "2px solid black";

    // Une los bordes contiguos para evitar líneas dobles.
    tabla.style.borderCollapse = "collapse";
}


// FUNCIÓN QUE CREA UNA CLAUSURA (CLOSURE).
function crearManejador(fila, columna) {

    // Devuelve una función que se ejecutará cuando se pulse la celda.
    // La función interior conserva los valores de fila y columna
    // recibidos, aunque el bucle ya haya terminado.
    return function () {

        // Muestra la posición de la celda en un cuadro de alerta.
        // Sumamos 1 porque los índices de los bucles empiezan en 0.
        alert(
            "Columna: " + (columna + 1) +
            ", Fila: " + (fila + 1)
        );
    };
}
