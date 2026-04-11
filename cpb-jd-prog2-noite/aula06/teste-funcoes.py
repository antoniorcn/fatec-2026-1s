def cabecalho():
    print("### Programação 2 ###")
    print("### Fatec Carapicuiba ###")
    print("### Autor: Antonio ###")


def somar( numero1, numero2 ):
    resultado = numero1 + numero2
    print("Resultado: ", resultado)
    return resultado

def procurar_numeros( lista, numero_procurar ): 
    if lista[0] == numero_procurar:
        return True
    elif lista[1] == numero_procurar:
        return True
    elif lista[2] == numero_procurar:
        return True
    return False


# print("executando a linha 8")
# cabecalho()
# print("executando a linha 10")
# cabecalho()
# print("executando a linha 12")

# cabecalho()
# print("Executando a funcao somar...")
# r1 = somar(10, 20)
# r2 = somar(r1, 50)
# r3 = somar(r1, r2)

cabecalho()
print("Procurar numero")

b = procurar_numeros( [10, 20, 30], 50 )
print("Numero 50 encontrado ==> ", b)

b = procurar_numeros( [10, 20, 30], 30 )
print("Numero 30 encontrado ==> ", b)