class pAJAX {
    constructor() {
        this.p = new XMLHttpRequest();
    }

    //Defino el metodo:
    peticion(url, metodo, fRes = null, param = "") {
        metodo = metodo.toUpperCase();
        if (fRes != null) {
            this.p.onreadystatechange = function () {
                if ((this.readyState == 4) && (this.status == 200))
                    fRes(this.responseText);
            };
        }
        if (metodo == "GET") {
            if (param.trim().length > 0)
                url += "?" + param;
            this.p.open(metodo, url);
            this.p.setRequestHeader("Acept", "application/json; charset=utf-8");
            this.p.send();
        }
        if (metodo == "POST") {
            this.p.open(metodo, url);
            //this.p.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            this.p.setRequestHeader("Acept", "application/json");
            this.p.setRequestHeader("Content-Type", "application/json; charset=utf-8");
            this.p.send(param);
        }
        if (metodo == "DELETE") {
            this.p.open(metodo, url);
            this.p.setRequestHeader("Accept", "application/json");
            this.p.send();
        }
        if (metodo == "PUT") {
            this.p.open(metodo, url);
            this.p.setRequestHeader("Accept", "application/json");
            this.p.setRequestHeader("Acept", "application/json; charset=utf-8");
            this.p.send(param);
        }
    }
}