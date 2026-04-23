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
    for x in range(350, 400):
        tela.set_at( (x, 300), (255, 255, 255) )

    pygame.display.update()
    clock.tick( 30 ) # quantidade de FPS

    # Captura de Eventos
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
           jogando = False
