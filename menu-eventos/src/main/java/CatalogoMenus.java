

import java.util.List;

/** Catálogo compartido por los dos servlets. Clase proporcionada. */
public final class CatalogoMenus {
    private CatalogoMenus() { }

    public static List<Menu> obtenerMenus() {
        return List.of(
            new Menu("tradicional", "Menú tradicional", 16.0, true),
            new Menu("vegetal", "Menú vegetal", 14.0, true),
            new Menu("brunch", "Menú brunch", 12.0, false)
        );
    }

    /** Devuelve el menú correspondiente o null si el código no existe. */
    public static Menu buscarPorCodigo(String codigo) {
        for (Menu menu : obtenerMenus()) {
            if (menu.getCodigo().equals(codigo)) {
                return menu;
            }
        }
        return null;
    }
}
