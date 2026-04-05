📇 Lista de Contactos - Android Kotlin
Esta aplicação Android foi desenvolvida para o Exercício 5 da unidade curricular UC00609. O foco principal é a gestão de uma lista dinâmica de contactos utilizando uma arquitetura baseada em objetos e navegação entre múltiplas janelas.

🧩 Extensões Recomendadas (VS Code)
Para uma melhor experiência de desenvolvimento, este repositório inclui um ficheiro `extensions.json` que sugere:
- Kotlin (fwcd.kotlin)
- Android iOS Emulator (adpyke) para gestão visual do dispositivo.
- Gradle for Java para execução de tarefas facilitada.

🚀 Funcionalidades Implementadas
📋 Visualização Dinâmica (ListView)
Custom Adapter: Implementação de um adaptador personalizado (ContactoAdapter) para exibir fotos, nomes e telefones numa lista elegante.

Splash Screen: Ecrã de boas-vindas com temporizador de 3 segundos antes de carregar a lista principal.

⚙️ Gestão de Contactos (CRUD)
Adicionar: Ecrã de formulário (EditorActivity) para inserir novos nomes, telefones e emails.

Consultar (Resumo): Ao clicar num contacto, abre-se um ecrã de detalhes (DetalheActivity) com a informação completa (Figura 2 do enunciado).

Editar: Clique longo num contacto permite abrir o formulário com os dados preenchidos para atualização imediata na lista.

Eliminar: Funcionalidade de remoção de contactos através de um clique longo (Long Press) em qualquer item da lista.

🛠️ Arquitetura Técnica
Linguagem: Kotlin

Persistência Temporária: Gestão de dados em memória através de uma MutableList de objetos.

Comunicação entre Activities:

Utilização de Intent com objetos Serializable para transporte de dados complexos.

Utilização de onActivityResult para capturar dados retornados pelo formulário.

UI Components:

ListView + ArrayAdapter

ProgressBar (Indeterminate)

EditText com tipos de entrada específicos (phone, textEmailAddress)

📂 Estrutura do Projeto
Plaintext
app/src/main/java/com/mh/listacontactos/
 ├── Contacto.kt        <-- Data Class (Modelo do Objeto)
 ├── ContactoAdapter.kt <-- O "Motor" da ListView
 ├── MainActivity.kt    <-- Gestão da lista e cliques
 ├── SplashActivity.kt  <-- Ecrã de entrada
 ├── DetalheActivity.kt <-- Resumo do contacto
 └── EditorActivity.kt  <-- Formulário de inserção
⚙️ Como Executar
Clonar e Entrar na Pasta:

PowerShell
git clone https://github.com/Maria-Helena-Almeida/listacontactos.git
cd listacontactos
Compilar e Instalar:

PowerShell
./gradlew installDebug --no-configuration-cache
Comandos ADB Úteis (Caso necessário):

PowerShell
# Abrir a App manualmente
adb shell am start -n com.mh.listacontactos/.SplashActivity

# Forçar paragem da App
adb shell am force-stop com.mh.listacontactos
🆘 Resolução de Problemas Comuns
Se o comando installDebug falhar por ficheiros bloqueados:

No PowerShell, mata os processos Java:

PowerShell
Stop-Process -Name "java" -Force -ErrorAction SilentlyContinue
Limpa a pasta de Build manualmente:

PowerShell
Remove-Item -Recurse -Force app/build -ErrorAction SilentlyContinue
👤 Autor
Maria Helena Alves de Almeida
Projeto prático de Programação de Dispositivos Móveis.