# def somar(n1, n2=0, n3=0, n4=0, n5=0):
#     return n1 + n2 + n3 + n4 + n5

def somar( n1 : float, *numeros : float ):
    """ Esta funcao soma os numeros, onde deve
    receber ao menor um valor numerico
    """
    print("Numeros Recebidos: ", numeros)
    soma = n1
    for n in numeros:
        soma += n
    return soma


r1 = somar(10, 20, 30, 40)
print("Resultado da soma (10, 20, 30, 40): ", r1)

r1 = somar(5, 6)
print("Resultado da soma (5, 6): ", r1)

r1 = somar(2, 3, 4)
print("Resultado da soma (2, 3, 4): ", r1)

r1 = somar(10)
print("Resultado da soma (10): ", r1)
