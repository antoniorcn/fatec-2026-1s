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
    pygame.draw.line( tela, (255, 255, 0), (30, 30), (400, 300), 5 )
    pygame.draw.line( tela, (255, 255, 0), (770, 30), (400, 300), 5 )
    pygame.draw.line( tela, (255, 255, 0), (30, 570), (400, 300), 5 )
    pygame.draw.line( tela, (255, 255, 0), (770, 570), (400, 300), 5 )

    pygame.display.update()
    clock.tick( 30 ) # quantidade de FPS

    # Captura de Eventos
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
           jogando = False
