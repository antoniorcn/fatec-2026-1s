

lista = [0, 1, 1, 2, 3, 5, 8, 13, 21]

print(lista)
lista.append( 34 )
print(lista)
lista.insert( 1, 0 )
print(lista)
lista.remove(13)
print(lista)

# del lista[6]
valor = lista.pop(6)
print("Removendo o valor ==> ", valor )
print(lista)

# E - Espadas
# C - Copas
# P - Paus
# O - Ouros
#       0     1      2     3     4
mao = ["7E", "10P", "5O", "2C", "AE"] 

print(mao)
carta1 = mao.pop(1)
print("Entregando a carta: ", carta1)
print(mao)

# tem_as_ouros = mao.index("AO")
tem_as_ouros = "AO" in mao
if "AE" in mao:
    as_espadas = mao.index("AE")
    print("As de espadas está na posicao", as_espadas)
print("Tem As de Ouros: ", tem_as_ouros)
