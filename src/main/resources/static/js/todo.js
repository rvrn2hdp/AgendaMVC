/* 
 * 
 * En este ejemplo, usaremos Vanilla JS
 * 
 */

let cantidadTareas = 1;

document.addEventListener("DOMContentLoaded", (event) => {
    
//    //Crear las tareas de test
//    const tarea1 = {
//        id: 1,
//        descripcion: "Estudiar JS"
//    };
//    
//    const tarea2 = {
//        id: 1,
//        descripcion: "Estudiar HTML5"
//    };
//    
//    cargarTareas(tarea1);
//    cargarTareas(tarea2);
});

//function nuevaTarea() { Forma antigua
//    
//}

//A parte de JS 15 - Arrow functions
const nuevaTarea = () => {
    document.getElementById("descri").value = ""
    let tareaModal = new bootstrap.Modal(document.getElementById("tareaModal"), {});
    tareaModal.show()
}

const guardarTarea = () => {
    cargarTareas(crearTarea(cantidadTareas, document.getElementById("descri").value));
}

const crearTarea = (id, descripcion) => {
    return {
        id: id,
        descripcion : descripcion
    };
}

const cargarTareas = (tarea) => {
    
    //Crear un array de tareas:
    const tareas = [];
    
    //Añadir la tarea enviada al array:
    tareas.push(tarea);
    
    //Renderizar una fila por tarea en el array...
    tareas.map((item) => {
        
        //Bindings que almacenarán los elementos html
        let ul = document.getElementsByTagName("ul")[0];
        let li = document.createElement("li");
        let checkbox = document.createElement("input");
        let btnEdit = document.createElement("button");
        let btnDel = document.createElement("button");
        let iconEdit = document.createElement("i");
        let iconDel = document.createElement("i");
        
        //Añadir propiedades y estilos
        li.classList.add("list-group-item");
        
        checkbox.type = "checkbox";
        checkbox.className = "form-check-input me-4";
        
        btnEdit.className = "btn btn-sm btn-warning float-end";
        btnEdit.setAttribute("data-bs-toggle", "tooltip");
        btnEdit.setAttribute("data-bs-placement", "bottom");
        btnEdit.setAttribute("title", "Editar");
        
        iconEdit.className = "far fa-edit";
        btnEdit.appendChild(iconEdit);
        
        btnDel.className = "btn btn-sm btn-danger float-end ms-2";
        btnDel.setAttribute("data-bs-toggle", "tooltip");
        btnDel.setAttribute("data-bs-placement", "bottom");
        btnDel.setAttribute("title", "Borrar");
        
        iconDel.className = "far fa-trash-alt";
        btnDel.appendChild(iconDel);
        
        //Crear los list items
        li.appendChild(checkbox);
        li.appendChild(document.createTextNode(`${item.id} - ${item.descripcion}`)); //Usar template strings
        li.appendChild(btnDel);
        li.appendChild(btnEdit);
        
        //Añadir los li al ul
        ul.appendChild(li);
        
        cantidadTareas++;
    });
    
}


