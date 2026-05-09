import random

for _ in range(50):
    numero = random.randint(0, 38)
    if numero > 36:
        numero = 0
    print(f"Numero sorteado: {numero}")