print ("=" * 30)
print ("Nombre : Eydan Suarez")
print ("Ciclo: 2º DAM")
print ("Centro: IES Vicente Medina")
print("=" * 30)

######################################

print("Hola")
print("Nota:", 8.5) # varios valores separados por comas
print("A", "B", "C", sep="-") # cambia el separador: ABC
print("Sin salto", end=" ")   # cambia el final de línea
print("de línea")
print("=" * 30)
nombre = input("¿Cómo te llamas? ")
print("Encantado,",nombre)

#######################################
#RESERVA CINE

print("RESERVA DE ENTRADAS")
print("=" * 30)
nombre = input("Nombre del cliente: ") # se detiene siempre con el input para introducir los datos que queremos
pelicula = input("Pelicula: ")
sala = input("Sala: ")

print()
print("Reserva confirmada")
print("="*30)
print("Cliente: ", nombre)
print("Pelicula: ", pelicula)
print("Sala: ", sala)

################################
#ERRORES
# print("Inicio del programa" --> no estamos cerrando el parentesis
# nombre = "Ana"
# print(nombre)

# print("Inicio del programa")
# nombre = "Ana"
# print(Nombre) # la variable se llama 'nombre' y ponemos 'Nombre'

# Error de sintaxis el primero, antes de ejecutar nada, python ni siquiera empieza
# Error de ejecucion el segundo, al llegar a la línea

################################
#NOMBRES DE VARIABLES Y OTROS ELEMENTOS

nombre_alumno = "luis"
nota1 = 7
nota2 = 8.5


def CalcularMeida(a, b):
    return (a + b) / 2


media = CalcularMeida(nota1, nota2)
print("Media de", nombre_alumno, ":", media)