import pygame
pygame.init()
tela = pygame.display.set_mode( (600, 400), 0, 32 )
jogando = True
while jogando:
    pygame.draw.circle( tela, (255, 255, 0), (300, 200), 150, 0)
    pygame.display.update()
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            jogando = False