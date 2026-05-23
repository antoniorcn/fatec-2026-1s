from typing import Any

import pygame

def recortar( spritesheet, g_id, colunas, largura, altura):
    coluna = g_id % colunas
    linha = g_id // colunas
    x_img = coluna * largura
    y_img = linha * altura
    return spritesheet.subsurface( (x_img, y_img), (largura, altura) )

class Heroi( pygame.sprite.Sprite ):
    def __init__(self, x, y, piso, spritesheet, colunas, largura, altura ) -> None:
        super().__init__()
        self.animacao = [27, 28, 29, 30, 31, 32, 33, 34, 35]
        self.frame = 0
        self.g_id = 0
        self.x = x
        self.y = y
        self.piso = piso
        self.vel_x = 0
        self.vel_y = 0
        self.rect = pygame.Rect( (x, y), (largura, altura) )
        self.spritesheet = spritesheet
        self.colunas = colunas
        self.largura = largura
        self.altura = altura
        self.image = recortar(spritesheet, self.animacao[self.g_id],
                              colunas, largura, altura)

    def update(self) -> None:
        super().update()
        gravidade = 0.098
        self.vel_y = self.vel_y + (gravidade * 0.5)
        self.rect.move_ip((self.vel_x, int(self.vel_y)))
        if self.rect.y >= self.piso:
            self.rect.y = self.piso
        self.image = recortar(self.spritesheet, self.animacao[ self.g_id ],
                              self.colunas, self.largura, self.altura)
        if self.frame % 50 == 0:
            self.g_id = self.g_id + 1
        if self.g_id >= self.colunas:
            self.g_id = 0
        self.frame += 1


class Boss( pygame.sprite.Sprite ):
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




LARGURA = 800
ALTURA = 600

tela = pygame.display.set_mode( (LARGURA, ALTURA), 0, 32 )

cenario1 = pygame.image.load("./images/cenario1.png").convert_alpha()
cenario2 = pygame.image.load("./images/cenario2.png").convert_alpha()
heroi_spritesheet = pygame.image.load("./images/heroi.png")\
    .convert_alpha()
heroi_spritesheet = pygame.transform.scale(heroi_spritesheet, (1152, 512))
    
boss_spritesheet = pygame.image.load("./images/boss.png")\
    .convert_alpha()
boss = recortar( boss_spritesheet, 12, 4, 96, 96)

cenario1_ready = pygame.transform.scale(cenario1, (LARGURA, ALTURA))
cenario2_ready = pygame.transform.scale(cenario2, (LARGURA, ALTURA))
boss_ready = pygame.transform.scale(boss, (128, 128))


heroi_sprite = Heroi(50, 4, 400, heroi_spritesheet, 9, 128, 128)
boss_sprite = Boss(650, 400, boss_ready)

personagens = pygame.sprite.Group()
personagens.add( boss_sprite )
personagens.add( heroi_sprite )


jogando = True

while jogando:

    # Calcula as regras
    boss_sprite.vel_x = int((heroi_sprite.rect.x - boss_sprite.rect.x) * 0.01)
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
            if e.key == pygame.K_LEFT:
                heroi_sprite.vel_x = -1
            if e.key == pygame.K_SPACE:
                heroi_sprite.vel_y = -5          
        if e.type == pygame.KEYUP:
            if e.key == pygame.K_RIGHT or e.key == pygame.K_LEFT:
                heroi_sprite.vel_x = 0               
