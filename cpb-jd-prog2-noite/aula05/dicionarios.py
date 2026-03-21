mochila = { "arma": "espada", "protecao": "escudo",
           "bombas": 10, "flechas": 15}
print(mochila)
mochila["instrumento"] = "ocarina"
print(mochila)
print("Há bombas na mochila: ", mochila["bombas"])

mochila["flechas"] = 20

if "instrumento" in mochila:
    print("Há instrumento na mochila: ", mochila["instrumento"])
else:
    print("Não há instrumentos na mochila")

print("Chaves: ", mochila.keys())

print("Valores: ", mochila.values())

print("Items: ", mochila.items())
