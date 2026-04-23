import pygame
from pygame import time

pygame.init()


tela = pygame.display.set_mode((800, 600), 0, 32)

#   Red      Green    Blue   Opacidade
# 00000000 00000000 00000000 00000000
# 0  - 255 0  - 255 0  - 255 0  - 255

jogando = True
clock = time.Clock()

while jogando:    # Loop do Jogo
    # Calcular regras

    # Desenhar na Tela
    pygame.draw.circle( tela, (0, 0, 255), (400, 300), 200.0, 5)
    

    pygame.display.update()
    clock.tick( 30 ) # quantidade de FPS

    # Captura de Eventos
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
           jogando = False
