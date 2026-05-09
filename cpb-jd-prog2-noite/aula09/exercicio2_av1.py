import random
import math

distancia = random.randint(5, 101)
andar = random.randint(1, 16)
print("Jogo do estilingue")

print(f"Você esta a {distancia} metro do predio")
print(f"e precisa quebrar a janela do {andar}o andar")

angulo = float(
    input("Em qual angulo voce vai arremessar a pedra ?")
    )

ang_rad = angulo * math.pi / 180

altura = andar * 3
d = math.sqrt((distancia ** 2) + (altura ** 2))

x1 = d * math.cos( ang_rad )
y1 = d * math.sin( ang_rad )

h_min = (andar - 1) * 3
h_max = andar * 3

print(f"Voce acertou o {y1:2.0f}o andar")

if x1 > (distancia * 0.9) and x1 < (distancia * 1.1) and\
    y1 > h_min and y1 < h_max:
    print("Parabens !!! Voce acertou")
else:
    print("Que pena !!! Voce errou")

