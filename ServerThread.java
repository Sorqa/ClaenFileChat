package com.test.sku.network.pds;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static com.test.sku.network.pds.PDSRequest.CMD;

public class ServerThread extends Thread 
{

    private Socket clientSocket;
    private ObjectInputStream oin;
    private ObjectOutputStream oos;
    
    static FileIO f =new FileIO();


    public ServerThread(Socket clientSocket) 
    {
        this.clientSocket = clientSocket;
        try {
            oin = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() 
    {
        try {
            while (true) 
            {
            	PDSRequest request = (PDSRequest) oin.readObject();
            	switch(request.getCmd()) 
            	{
            	case UPLOAD: 
            		PDSResponse res = new PDSResponse(upload(request) ? "업로드 성공":"업로드 실패");
            		oos.writeObject(res);
            		oos.flush();
            		break;
            	case LIST: //직렬화 파일 로드, 리스트 추출, res 오브젝트에 설정, res오브젝트 전송 
            		
            		res = new PDSResponse();
            		res.setList(f.deserialize());
            		oos.writeObject(res);
            		oos.flush();
            		break;
            	case DETAIL: //파일 번호/이름로 검색	
            		oos.writeObject(new PDSResponse(find(request.getPds())));
            		oos.flush();          	
            		break;
            	case UPDATE: //업데이트	            		
            		res = new PDSResponse();
            		
            		res.setList(update(request.getPds()));
            		            		
            		oos.writeObject(res);
            		oos.flush();
            		
            		break;
            	case DELETE: //삭제
            		            		
            		oos.writeObject(new PDSResponse(delete(request)));
            		oos.flush();

            		break;
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String delete(PDSRequest req) {
    	FileIO fio = new FileIO();
    	
    	List<PDSVO> list = fio.deserialize();
		boolean deltedimg = fio.delete(fio.find(req.getPds()));
		if(deltedimg) {
			list.remove(list.indexOf(req.getPds()));
			
			fio.serialize(list);
			return "파일 삭제 성공";
		}else {
			return "파일 삭제 실패";
		}
			
		
    }
    
    
    
    
    private boolean upload(PDSRequest req)
    {
    	PDSVO pds = req.getPds();
		FileIO fio = new FileIO();
		boolean saved = fio.save(pds.getFname(), pds.getFdata());
		if(!saved) return false;
		
		pds.setFsize(pds.getFdata().length);
		List<PDSVO> list = fio.deserialize();
		if(list.size()==0) {
			pds.setNo(1);
		}else {
    		//Collections.sort(list);
    		PDSVO last = list.get(list.size()-1);
    		pds.setNo(last.getNo()+1);
    		pds.setFdata(null);
		}
		list.add(pds);
		fio.serialize(list);

    	return true;
    }
    
    private PDSVO find(PDSVO key)
    {
    	PDSVO found = new FileIO().find(key);		
		return found;         			
    }
    
    private List<PDSVO>  update(PDSVO key)
    {
    	
    	FileIO fio = new FileIO();
    	
    	List<PDSVO> list = fio.deserialize();
    	
    	PDSVO pds = fio.find(key);
		pds.setDesc(key.getDesc());
		pds.setAuthor(key.getAuthor());
		
		list.set(list.indexOf(key), pds);
		
		f.serialize(list);
		return list;	
    }
}