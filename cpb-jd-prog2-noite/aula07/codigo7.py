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
    lista_pontos = [ (400, 20), (20, 300), (780, 300) ]
    pygame.draw.polygon( tela, (0, 255, 255), lista_pontos, 0)
    pygame.display.update()
    clock.tick( 30 ) # quantidade de FPS

    # Captura de Eventos
    for e in pygame.event.get():
        print(f"Type: {e.type}\t", end="")
        if e.type == pygame.QUIT:
           jogando = False
        elif e.type == pygame.MOUSEMOTION:
            print(f"Pos: {e.pos}\t", end="")
        elif e.type in [pygame.MOUSEBUTTONDOWN, pygame.MOUSEBUTTONUP]:
            print(f"Button: {e.button}\t", end="")
        elif e.type in [pygame.KEYDOWN, pygame.KEYUP]:
            print(f"Key: {e.key}\t", end="")
        print()
           
