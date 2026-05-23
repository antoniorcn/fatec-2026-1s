from typing import Any

import pygame

class Heroi( pygame.sprite.Sprite ):
    def __init__(self, x, y, imagem ) -> None:
        super().__init__()
        self.x = x
        self.y = y
        self.vel_x = 0
        self.vel_y = 0
        self.rect = pygame.Rect( (x, y), (128, 128) )
        self.image = imagem

    def update(self) -> None:
        super().update()
        self.rect.move_ip((self.vel_x, self.vel_y))


class Boss( pygame.sprite.Sprite ):
    def __init__(self, x, y, imagem ) -> None:
        super().__init__()
        self.x = x
        self.y = y
        self.rect = pygame.Rect( (x, y), (128, 128) )
        self.image = imagem        





LARGURA = 800
ALTURA = 600

tela = pygame.display.set_mode( (LARGURA, ALTURA), 0, 32 )

cenario1 = pygame.image.load("./images/cenario1.png").convert_alpha()
cenario2 = pygame.image.load("./images/cenario2.png").convert_alpha()
heroi = pygame.image.load("./images/heroi.png")\
    .convert_alpha()\
    .subsurface( ((0, 192), (64, 64)) )
boss = pygame.image.load("./images/boss.png")\
    .convert_alpha()\
    .subsurface( ((96, 96), (96, 96)) )

cenario1_ready = pygame.transform.scale(cenario1, (LARGURA, ALTURA))
cenario2_ready = pygame.transform.scale(cenario2, (LARGURA, ALTURA))
heroi_ready = pygame.transform.scale(heroi, (128, 128))
boss_ready = pygame.transform.scale(boss, (128, 128))


heroi_sprite = Heroi(50, 400, heroi_ready)
boss_sprite = Boss(650, 400, boss_ready)

personagens = pygame.sprite.Group()
personagens.add( boss_sprite )
personagens.add( heroi_sprite )


jogando = True

while jogando:

    # Calcula as regras
    personagens.update()

    # Atualiza a tela
    tela.blit( cenario1_ready, (0, 0) )
    tela.blit( cenario2_ready, (0, 0) )
    personagens.draw( tela )
    pygame.display.update()

    # Capturar os eventos
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            jogando = False
        if e.type == pygame.KEYDOWN:
            if e.key == pygame.K_RIGHT:
                heroi_sprite.vel_x = 1
