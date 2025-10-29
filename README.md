# 💻 GitHub API Client — Proyecto JavaFX

Aplicación de escritorio desarrollada en **JavaFX** que consume la **API pública de GitHub** para mostrar información detallada de usuarios, sus repositorios y un análisis gráfico de los lenguajes utilizados.

---

## 🧩 Funcionalidades principales

✅ Consulta cualquier usuario de GitHub mediante su nombre.  
✅ Muestra:
- Avatar del usuario (imagen de perfil)
- Nombre, ubicación y seguidores
- Fecha de creación del perfil  
✅ Lista todos los **repositorios públicos** del usuario.  
✅ Analiza los **lenguajes utilizados** en sus repos y los muestra en un gráfico circular interactivo.  
✅ Interfaz amigable, limpia y totalmente dinámica.  

---

## 🖼️ Vista general

Ejemplo con el usuario **@a**:

![Ejemplo de ejecución](https://user-images.githubusercontent.com/your-demo-image.png)

*(La gráfica muestra el porcentaje de lenguajes en los repos públicos del usuario.)*

---

## ⚙️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|-------------|----------|-----|
| **Java** | 21 | Lógica general del programa |
| **JavaFX** | 21 | Interfaz gráfica |
| **Maven** | 3.9 | Gestión de dependencias |
| **Jackson Databind** | 2.17 | Parseo de JSON desde la API |
| **GitHub REST API** | v3 | Fuente de datos remota |

---

## 📦 Estructura del proyecto

src/
└── main/
├── java/
│ └── com/mycompany/api/github/
│ ├── domain/ # Clases modelo: User, Repo
│ ├── service/ # Lógica de conexión con la API
│ ├── ui/ # Controladores y MainApp JavaFX
│ └── util/ # Utilidades (formatter, cálculos)
└── resources/
└── ui/ # Archivos .fxml y recursos visuales

## 🚀 Ejecución del proyecto

Asegúrate de tener instalado **Java 21** y **Maven**.


bash
# Clonar el repositorio
git clone https://github.com/MarvinTorresS/api-github.git
cd api-github

# Compilar y ejecutar
mvn clean javafx:run

<img width="912" height="527" alt="image" src="https://github.com/user-attachments/assets/e39cc9f7-022a-406e-a7aa-b7b4f579cc8f" />

Escribir el nombre del repositorio, presionar el boton buscar y aparece la informacion




